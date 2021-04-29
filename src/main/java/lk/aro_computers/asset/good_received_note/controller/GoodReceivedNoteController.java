package lk.aro_computers.asset.good_received_note.controller;

import lk.aro_computers.asset.common_asset.model.enums.LiveDead;
import lk.aro_computers.asset.good_received_note.entity.GoodReceivedNote;
import lk.aro_computers.asset.good_received_note.service.GoodReceivedNoteService;
import lk.aro_computers.asset.item.entity.Item;
import lk.aro_computers.asset.item.service.ItemService;
import lk.aro_computers.asset.ledger.entity.Ledger;
import lk.aro_computers.asset.ledger.service.LedgerService;
import lk.aro_computers.asset.purchase_order.entity.PurchaseOrder;
import lk.aro_computers.asset.purchase_order.entity.enums.PurchaseOrderStatus;
import lk.aro_computers.asset.purchase_order.service.PurchaseOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping( "/goodReceivedNote" )
public class GoodReceivedNoteController {
  private final GoodReceivedNoteService goodReceivedNoteService;
  private final PurchaseOrderService purchaseOrderService;
  private final LedgerService ledgerService;
  private final ItemService itemService;

  public GoodReceivedNoteController(GoodReceivedNoteService goodReceivedNoteService,
                                    PurchaseOrderService purchaseOrderService, LedgerService ledgerService,
                                    ItemService itemService) {
    this.goodReceivedNoteService = goodReceivedNoteService;
    this.purchaseOrderService = purchaseOrderService;
    this.ledgerService = ledgerService;
    this.itemService = itemService;
  }


  @GetMapping
  public String notCompleteAll(Model model) {
    model.addAttribute("notCompleteAll",
                       purchaseOrderService.findByPurchaseOrderStatus(PurchaseOrderStatus.NOT_COMPLETED));
    return "goodReceivedNote/goodReceivedNote";
  }

  @GetMapping( "/{id}" )
  public String grnAdd(@PathVariable Integer id, Model model) {
    model.addAttribute("purchaseOrderDetail", purchaseOrderService.findById(id));
    model.addAttribute("goodReceivedNote", new GoodReceivedNote());
    return "goodReceivedNote/addGoodReceivedNote";
  }

  @PostMapping( "/received" )
  public String saveGRN(@Valid @ModelAttribute GoodReceivedNote goodReceivedNote, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes, Model model) {
    if ( bindingResult.hasErrors() ) {
      return "redirect:/goodReceivedNote/".concat(String.valueOf(goodReceivedNote.getPurchaseOrder().getId()));
    }
//New Leger add to add system as new item on ledger
    GoodReceivedNote goodReceivedNoteDb = goodReceivedNoteService.persist(goodReceivedNote);

    for ( Ledger ledger : goodReceivedNote.getLedgers() ) {

      Item item = itemService.findById(ledger.getItem().getId());

      Ledger ledgerDB = ledgerService.findByItemAndSellPrice(item,
                                                             item.getSellPrice());
//if there is on value in ledger need to update it
      if ( ledgerDB != null ) {
        //before update need to check price and expire date
        if ( ledgerDB.getSellPrice().equals(ledger.getSellPrice()) ) {
          ledgerDB.setQuantity(ledgerDB.getQuantity() + ledger.getQuantity());
          ledgerDB.setGoodReceivedNote(goodReceivedNoteDb);
          ledgerService.persist(ledgerDB);
          System.out.println("same item");
        } else {
          System.out.println("price change item");
          ledger.setGoodReceivedNote(goodReceivedNoteDb);
          ledger.setLiveDead(LiveDead.ACTIVE);
          ledgerService.persist(ledger);
        }
      } else {
        System.out.println("new item");
        ledger.setGoodReceivedNote(goodReceivedNoteDb);
        ledger.setLiveDead(LiveDead.ACTIVE);
        ledgerService.persist(ledger);
      }
    }


    PurchaseOrder purchaseOrderDb = purchaseOrderService.findById(goodReceivedNoteDb.getPurchaseOrder().getId());
    purchaseOrderDb.setPurchaseOrderStatus(PurchaseOrderStatus.NOT_PROCEED);
    purchaseOrderService.persist(purchaseOrderDb);

    return "redirect:/goodReceivedNote";
  }

}
