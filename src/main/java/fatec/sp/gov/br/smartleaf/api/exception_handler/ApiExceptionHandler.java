package fatec.sp.gov.br.smartleaf.api.exception_handler;

import fatec.sp.gov.br.smartleaf.domain.exception.BusinessException;
import fatec.sp.gov.br.smartleaf.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var detail = ex.getMessage();
        var problemType = ProblemType.ERRO_DE_NEGOCIO;
        var type = URI.create("https://localhost:8080/errors/business-error");

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(type);

        return problemDetail;
    }


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontradaException(BusinessException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var detail = ex.getMessage();
        var problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        var type = URI.create("https://localhost:8080/errors/entidade-nao-encontrada");

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(type);

        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var title = "Inválido";
        var type = URI.create("https://localhost:8080/errors/campos-invalidos");
        var detail = "Mensagem Http não é legível. Corrija a sintaxe";

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(type);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var title = "Um ou mais campos inválidos";
        var type = URI.create("https://localhost:8080/errors/campos-invalidos");
        var problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getLocalizedMessage());
        problemDetail.setTitle(title);
        problemDetail.setType(type);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }
}

