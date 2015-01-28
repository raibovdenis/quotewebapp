package ru.dz.quotewebapp.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.dz.quotewebapp.model.Quote;
import ru.dz.quotewebapp.model.QuoteNavigation;
import ru.dz.quotewebapp.model.User;
import ru.dz.quotewebapp.util.PageUtil;
import ru.dz.quotewebapp.util.PageRequestUtil;

import ru.dz.quotewebapp.service.QuoteService;
import ru.dz.quotewebapp.service.UserService;

import java.util.List;


@Controller
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private UserService userService;


    private List<User> getUserList(){
        PageRequestUtil pageRequestUtil = new PageRequestUtil(0, 0, "userName", PageRequestUtil.SORT_DIR_ASC);
        List<User> userList = this.userService.getUserList(pageRequestUtil);

        return userList;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getListQuote(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageRequestUtil.SORT_DIR_DESC, required = false) String sortDir,
            Model model
    ) {
        PageRequestUtil pageRequestUtil = new PageRequestUtil(pageNumber, pageSize, sortBy, sortDir);
        PageUtil page = this.quoteService.getQuoteListPage(pageRequestUtil);
        QuoteNavigation quoteNavigation = new QuoteNavigation();

        model.addAttribute("pageNumberCode", "pageNumber");
        model.addAttribute("pageSizeCode", "pageSize");
        model.addAttribute("sortByCode", "sortBy");
        model.addAttribute("sortDirCode", "sortDir");

        model.addAttribute("page", page);
        model.addAttribute("quoteNavigation", quoteNavigation);
        model.addAttribute("pageRequest", pageRequestUtil);

        return "index";
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public String addQuote(@ModelAttribute("quote") Quote quote, Model model) {
        model.addAttribute("userList", this.getUserList());

        return "quote";
    }

    @RequestMapping(value = "/quote", method = RequestMethod.POST)
    public String addQuote(
            @Valid @ModelAttribute("quote") Quote quote,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userList", this.getUserList());

            return "quote";
        }

        this.quoteService.addQuote(quote);
        redirectAttributes.addFlashAttribute("flashMessageSuccess", "Quote successfully created");

        return "redirect:/";
    }

}
