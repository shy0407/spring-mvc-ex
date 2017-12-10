package com.doubles.ex04.controller;

import com.doubles.ex04.domain.BoardVO;
import com.doubles.ex04.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Inject
    private BoardService boardService;

    // 게시글 입력 페이지
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() throws Exception {
        return "board/write";
    }

    // 게시글 입력 처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(@ModelAttribute("boardVO") BoardVO boardVO,
                            RedirectAttributes redirectAttributes) throws Exception {
        boardService.write(boardVO);
        redirectAttributes.addFlashAttribute("msg", "INSERTED");
        return "redirect:/board/list";
    }

    // 게시글 조회
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("bno") Integer bno, Model model) throws Exception {
        model.addAttribute("boardVO", boardService.read(bno));
        return "board/read";
    }

    // 게시글 수정 페이지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("bno") Integer bno, Model model) throws Exception {
        model.addAttribute("boardVO", boardService.read(bno));
        return "board/modify";
    }

    // 게시글 수정 처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(@ModelAttribute("boardVO") BoardVO boardVO,
                             RedirectAttributes redirectAttributes) throws Exception {
        boardService.modify(boardVO);
        redirectAttributes.addFlashAttribute("msg", "UPDATED");
        redirectAttributes.addAttribute("bno", boardVO.getBno());
        return "redirect:/board/read";
    }

    // 게시글 삭제
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") Integer bno, RedirectAttributes redirectAttributes) throws Exception {
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("msg", "DELETED");
        return "redirect:/board/list";
    }

    // 게시글 목록
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        model.addAttribute("list", boardService.list());
        return "board/list";
    }

    // 게시글 목록 + 페이징

    // 게시글 목록 + 페이징 + 검색


}
