package ${package}.resource.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.HttpHeaders;
import org.apache.log4j.Logger;
import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.util.HeaderRequestBean;
import ${package}.canonical.request.GenericCannonicalRequest;

public class CastingMapper{
  
  private static final Logger logger = Logger.getLogger( CastingMapper.class );
  
  public static Object validarParametrosEntrada( String msjTxIn, HttpHeaders httpHeaders, Object beanRequest ){
    BodyResponse response = new BodyResponse();
    HeaderRequestBean header = new HeaderRequestBean( httpHeaders );
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<HeaderRequestBean>> constraintViolations = validator.validate( header );
    logger.info( msjTxIn + "Evaluando header y body" );
    if( !constraintViolations.isEmpty() ){
      response.setCodigoRespuesta( Constantes.UNO );
      response.setMensajeError( "Datos de Cabecera incompletos, VERIFICAR:  " + header.toString() );
      logger.info( msjTxIn + "Header no cumple validacion" );
      return response;
    }
    String sBody = requestConfirmValues( beanRequest );
    if( !sBody.isEmpty() ){
      response.setCodigoRespuesta( Constantes.UNO );
      response.setMensajeError( "Datos de Body incompletos, VERIFICAR:  " + sBody );
      logger.info( msjTxIn + "Body no cumple con parametros obligatorios" );
      return response;
    }
    logger.info( "Validacion de campo plataforma sea adecuado a los valores permitidos" );
    logger.info( msjTxIn + "Validacion correcta de header y body" );
    response.setCodigoRespuesta( Constantes.CERO );
    return response;
  }
  
  public static String requestConfirmValues( Object beanRequest ){
    String msgError = Constantes.TEXTO_VACIO;
    if( beanRequest instanceof GenericCannonicalRequest ){
      return beanHaveViolations( beanRequest );
    }
    return msgError;
  }
  
  public static String beanHaveViolations( Object request ){
    String msgError = Constantes.TEXTO_VACIO;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    javax.validation.Validator validator = factory.getValidator();
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate( request );
    if( constraintViolations.size() >= 1 ){
      for( ConstraintViolation<Object> cv: constraintViolations ){
        msgError = cv.getPropertyPath() + " - " + cv.getMessage();
        break;
      }
    }
    return msgError;
  }
}
