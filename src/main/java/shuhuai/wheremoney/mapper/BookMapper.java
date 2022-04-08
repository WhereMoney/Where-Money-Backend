package shuhuai.wheremoney.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.wheremoney.entity.Book;
import shuhuai.wheremoney.entity.User;

import java.util.List;

@Mapper
public interface BookMapper {
    Integer insertBookSelective(Book book);

    List<Book> selectBookByUser(User user);

    Book selectBookByUserTitle(User user, String title);
}