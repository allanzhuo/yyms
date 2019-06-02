package net.laoyeye.yyms.pojo.vo;

/**
 * @author laoyeye.net
 * @Description: 服务端消息响应
 * @date 2019/6/1 21:51
 */
public class ResponseMessage {
    private String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
