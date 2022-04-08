package shuhuai.wheremoney.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.Book;
import shuhuai.wheremoney.entity.User;
import shuhuai.wheremoney.mapper.BookMapper;
import shuhuai.wheremoney.mapper.UserMapper;
import shuhuai.wheremoney.service.BillCategoryService;
import shuhuai.wheremoney.service.BookService;
import shuhuai.wheremoney.service.excep.book.TitleOccupiedException;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.service.excep.user.UserMissingException;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BillCategoryService billCategoryService;

    @Override
    public void addBook(String userName, String title, Integer beginDate) {
        if (userName == null || title == null || beginDate == null || beginDate < 1 || beginDate > 28) {
            throw new ParamsException("参数错误");
        }
        User user = userMapper.selectUserByUserName(userName);
        Book book = bookMapper.selectBookByUserTitle(user, title);
        if (book != null) {
            throw new TitleOccupiedException("标题已被占用");
        }
        book = new Book(user.getId(), title, beginDate);
        Integer result = bookMapper.insertBookSelective(book);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
        billCategoryService.addDefaultBillCategory(book.getId());
    }

    @Override
    public List<Book> getBook(String userName) {
        if (userName == null) {
            throw new ParamsException("参数错误");
        }
        User user = userMapper.selectUserByUserName(userName);
        if (user == null) {
            throw new UserMissingException("用户不存在");
        }
        return bookMapper.selectBookByUser(user);
    }
}