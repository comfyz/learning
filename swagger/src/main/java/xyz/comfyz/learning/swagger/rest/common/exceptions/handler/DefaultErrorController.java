package xyz.comfyz.learning.swagger.rest.common.exceptions.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ErrorMessage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class DefaultErrorController extends AbstractErrorController {

    @Value("${server.error.path:${error.path:/error}}")
    String errorPath;

    @Autowired
    public DefaultErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    public DefaultErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }

    @RequestMapping(produces = {"text/html"})
    public void handleHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ErrorMessage message = getMessage(request);
        response.setContentType("text/html");
        ServletOutputStream outputStream = response.getOutputStream();
        OutputStreamWriter out = new OutputStreamWriter(outputStream, "UTF-8");
        try {
            out.write("<html>");
            out.write("<head>");
            out.write("</head>");
            out.write("<body>");
            out.write("<h1>" + message.getCode() + "</h1>");
            out.write("<h2>" + message.getMsg() + "</h2>");
            out.write("</body>");
            out.write("</html>");
        } finally {
            out.flush();
            out.close();
        }
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<ErrorMessage> error(HttpServletRequest request) {
        return new ResponseEntity<>(getMessage(request), getStatus(request));
    }

    private ErrorMessage getMessage(HttpServletRequest request) {
        org.springframework.http.HttpStatus status = getStatus(request);
        ErrorMessage message;
        if (status == NOT_FOUND) {
            String url;
            if (!StringUtils.isEmpty(request.getAttribute("javax.servlet.error.request_uri"))) {
                url = request.getAttribute("javax.servlet.error.request_uri").toString();
            } else {
                url = request.getRequestURI();
            }
            String desc = "Request uri '" + url + "' not found";
            message = new ErrorMessage(NOT_FOUND.value(), desc);
        } else if (status == INTERNAL_SERVER_ERROR) {
            String desc = request.getAttribute("javax.servlet.error.message").toString();
            if (!StringUtils.hasText(desc)) {
                desc = request.getAttribute("javax.servlet.error.exception").toString();
                desc = desc.substring(desc.lastIndexOf(":") + 1);
            }
            message = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), desc.trim());
        } else {
            String desc = "Unknown error";
            message = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), desc);
        }
        return message;
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
