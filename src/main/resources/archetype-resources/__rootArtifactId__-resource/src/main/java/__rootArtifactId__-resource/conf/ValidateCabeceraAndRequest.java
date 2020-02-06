package ${package}.resource.conf;

import javax.ws.rs.core.HttpHeaders;
import org.apache.log4j.Logger;
import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.property.Constantes;
import ${package}.canonical.request.GenericCannonicalRequest;
import ${package}.resource.util.CastingMapper;

public class ValidateCabeceraAndRequest{
  
  private static final Logger logger = Logger.getLogger( ValidateCabeceraAndRequest.class );
  private BodyResponse        bodyResponseValidacion;
  private String              messageTransaction;
  HttpHeaders                 httpHeaders;
  GenericCannonicalRequest    request;
  
  public ValidateCabeceraAndRequest( HttpHeaders httpHeaders, GenericCannonicalRequest request,
      String messageTransaction ){
    this.httpHeaders = httpHeaders;
    this.request = request;
    this.messageTransaction = messageTransaction;
  }
  
  public void validate(){
    logger.info( this.messageTransaction + "Se procede a validar Parametros obligatorios." );
    this.bodyResponseValidacion = (BodyResponse)CastingMapper.validarParametrosEntrada( messageTransaction, httpHeaders,
        request );
  }
  
  public boolean isValid(){
    return Constantes.CERO.equals( this.bodyResponseValidacion.getCodigoRespuesta() );
  }
  
  public String getMensajeError(){
    return this.bodyResponseValidacion.getMensajeError();
  }
}
