package shuhuai.wheremoney.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shuhuai.wheremoney.response.Response;
import shuhuai.wheremoney.service.excep.BaseException;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.service.excep.common.TokenExpireException;
import shuhuai.wheremoney.service.excep.user.UserMissingException;
import shuhuai.wheremoney.service.excep.user.UserNameOccupiedException;
import shuhuai.wheremoney.service.excep.user.UserNamePasswordErrorException;

@Slf4j
public class BaseController {
    @ExceptionHandler(BaseException.class)
    public Response handleServiceException(BaseException e) {
        Response response = new Response();
        log.error(e.getStackTrace()[0] + "：" + e.getMessage());
        if (e instanceof UserNameOccupiedException) {
            response.setCode(400);
        } else if (e instanceof UserNamePasswordErrorException | e instanceof TokenExpireException) {
            response.setCode(401);
        } else if (e instanceof ParamsException) {
            response.setCode(422);
        } else if (e instanceof ServerException) {
            response.setCode(500);
        } else if (e instanceof UserMissingException) {
            response.setCode(404);
        }
        response.setMessage(e.getMessage());
        return response;
    }
}