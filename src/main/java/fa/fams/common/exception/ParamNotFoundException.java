package fa.fams.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such User")
public class ParamNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ParamNotFoundException() {
    super("ParamNotFoundException");
  }
}
