package fa.fams.common.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  protected Logger logger;

  public GlobalControllerExceptionHandler() {
    logger = LoggerFactory.getLogger(getClass());
  }

  public static final String DEFAULT_ERROR_VIEW = "error";

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ModelAndView handleConflict(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ModelAndView handleConstraintViolation(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(NotFound.class)
  public ModelAndView handleNotFound(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(BadRequest.class)
  public ModelAndView handleBadRequest(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 400);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(Forbidden.class)
  public ModelAndView handleForbidden(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 403);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(InternalServerError.class)
  public ModelAndView handleInternalServer(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 500);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler({ SQLException.class, DataAccessException.class })
  public ModelAndView databaseError(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
  public ModelAndView handleArray(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(NullPointerException.class)
  public ModelAndView handleNullPointer(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handleNumberFormat(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 403);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(ClassNotFoundException.class)
  public ModelAndView handleClassNotFound(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 404);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(DateTimeParseException.class)
  public ModelAndView handleDateTimeParse(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 500);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(IllegalStateException.class)
  public ModelAndView handleIllegalState(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 500);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(IOException.class)
  public ModelAndView handleIO(HttpServletRequest request, Exception exception)
      throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 500);
    mav.setViewName("error");
    return mav;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest request,
      Exception exception) throws Exception {
    if (AnnotationUtils.findAnnotation(exception.getClass(),
        ResponseStatus.class) != null) {
      throw exception;
    }
    logger
        .error("Request: " + request.getRequestURI() + " raised " + exception);
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", exception);
    mav.addObject("url", request.getRequestURL());
    mav.addObject("timestamp", new Date().toString());
    mav.addObject("status", 500);
    mav.setViewName("error");
    return mav;
  }
}
