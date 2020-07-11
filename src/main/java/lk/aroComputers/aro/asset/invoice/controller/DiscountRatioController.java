package lk.aroComputers.aro.asset.invoice.controller;


import lk.aroComputers.aro.asset.invoice.entity.DiscountRatio;
import lk.aroComputers.aro.asset.invoice.service.DiscountRatioService;
import lk.aroComputers.aro.util.interfaces.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/discountRatio")
public class DiscountRatioController implements AbstractController<DiscountRatio , Integer> {
    private final DiscountRatioService discountRatioService;

    public DiscountRatioController(DiscountRatioService discountRatioService) {
    this.discountRatioService = discountRatioService;
}

    private String commonThings(Model model, DiscountRatio discountRatio, Boolean addState) {
        model.addAttribute("discountRatio", discountRatio);
        model.addAttribute("addStatus", addState);
        return "discountRatio/addDiscountRatio";

    }

@GetMapping
    public String findAll(Model model) {
        model.addAttribute("discountRatios", discountRatioService.findAll());
        return "discountRatio/discountRatio";
    }
    @Override
    public String findById(Integer id, Model model) {
        return null;
    }
    @GetMapping( "/edit/{id}" )
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, discountRatioService.findById(id), false);
    }
    @PostMapping( value = {"/save", "/update"} )
    public String persist(@Valid @ModelAttribute DiscountRatio discountRatio, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if ( bindingResult.hasErrors() ) {
            return commonThings(model, discountRatio, true);
        }
        redirectAttributes.addFlashAttribute("discountRatios", discountRatioService.persist(discountRatio));
        // discountRatioService.persist(discountRatio);
        return "redirect:/discountRatio";
    }
    @GetMapping( "/delete/{id}" )
    public String delete(@PathVariable Integer id, Model model) {
        discountRatioService.delete(id);
        return "redirect:/discountRatio";
    }
    @GetMapping("/add")
    public String Form(Model model) {
        return commonThings(model, new DiscountRatio(), true);
    }

    @GetMapping( "/{id}" )
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("discountDetail", discountRatioService.findById(id));
        return "branch/branch-detail";
    }

}