package fatec.sp.gov.br.smartleaf.api.exception_handler;

import fatec.sp.gov.br.smartleaf.domain.exception.BusinessException;
import fatec.sp.gov.br.smartleaf.domain.exception.EntidadeNaoEncontradaException;
import fatec.sp.gov.br.smartleaf.domain.exception.ImagemNaoEncontradaException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var detail = ex.getMessage();
        var problemType = ProblemType.ERRO_DE_NEGOCIO;
        var type = ProblemType.ERRO_DE_NEGOCIO.getType();

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(problemType.getTitle());
        problemDetail.setType(type);

        return problemDetail;
    }

    @ExceptionHandler(ImagemNaoEncontradaException.class)
    public ProblemDetail handleImagemNaoEncontradaException(ImagemNaoEncontradaException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var detail = ex.getMessage();
        var title = ProblemType.IMAGEM_NAO_ENCONTRADA.getTitle();
        var type = ProblemType.IMAGEM_NAO_ENCONTRADA.getType();

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(type);

        return problemDetail;
    }


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontradaException(BusinessException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var detail = ex.getMessage();
        var title = ProblemType.ENTIDADE_NAO_ENCONTRADA.getTitle();
        var type = ProblemType.ENTIDADE_NAO_ENCONTRADA.getType();

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(type);

        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var title = ProblemType.MENSAGEM_INCOMPREENSIVEL.getTitle();
        var type = ProblemType.MENSAGEM_INCOMPREENSIVEL.getType();
        var detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";

        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(type);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        var title = ProblemType.CAMPO_INVALIDO.getTitle();
        var type = ProblemType.CAMPO_INVALIDO.getType();
        var detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(type);
        problemDetail.setProperty("errors", errors);


        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }
}

