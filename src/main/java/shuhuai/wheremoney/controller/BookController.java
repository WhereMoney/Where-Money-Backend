package shuhuai.wheremoney.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.wheremoney.response.Response;
import shuhuai.wheremoney.response.book.GetBookResponse;
import shuhuai.wheremoney.service.BookService;
import shuhuai.wheremoney.utils.TokenValidator;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/book")
@Api(tags = "账本管理")
@Slf4j
public class BookController extends BaseController {
    @Resource
    private BookService bookService;

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 400, message = "标题已被占用"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    @ApiOperation(value = "新建账本")
    public Response addBook(String title, Integer beginDate) {
        String userName = TokenValidator.getUser().get("userName");
        bookService.addBook(userName, title, beginDate);
        Response response = new Response(200, "新建账本成功", null);
        log.info("/api/user/add-book：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 404, message = "用户不存在")
    })
    @RequestMapping(value = "/get-book", method = RequestMethod.GET)
    @ApiOperation(value = "获得账本")
    public Response getBook() {
        String userName = TokenValidator.getUser().get("userName");
        Response response = new Response(200, "获得账本成功", new GetBookResponse(bookService.getBook(userName)));
        log.info("/api/user/get-book：" + response.getMessage());
        return response;
    }
}