package pe.com.claro.eai.postventas.siac.consulta.producto.domain.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import org.apache.log4j.Logger;
import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.externo.IDTAndIDFKeyProperties;
import pe.com.claro.common.property.externo.specialist.EspecialistCallPropertiesExterno;

public class UtilDomainException{
  
  public UtilDomainException( EspecialistCallPropertiesExterno specialistCallProperties ){
    super();
    this.specialistCallProperties = specialistCallProperties;
  }
  
  private static final Logger      logger = Logger.getLogger( UtilDomainException.class );
  EspecialistCallPropertiesExterno specialistCallProperties;
  
  public void processException( Exception e, String messageTransaction, String nameSP, String nameDatabase )
      throws DBException{
    logger.error( messageTransaction + "Ha ocurrido un error al invocar a la capa repository" );
    logger.error( messageTransaction, e );
    String descripcionError = null;
    try( StringWriter errors = new StringWriter(); ){
      e.printStackTrace( new PrintWriter( errors ) );
      descripcionError = String.valueOf( errors.toString() );
    }
    catch( IOException e1 ){
      logger.error( messageTransaction, e1 );
    }
    if( descripcionError.toUpperCase( Locale.getDefault() ).contains( Constantes.SQL_TIMEOUTEXCEPTION ) ){
      logger.info( messageTransaction + Constantes.MENSAJEERRORTIMEOUT );
      throw new DBException(
          specialistCallProperties.getStringValueProperties( IDTAndIDFKeyProperties.IDT1.getListaValores().get( 1 ) ),
          specialistCallProperties.getStringValueProperties( IDTAndIDFKeyProperties.IDT1.getListaValores().get( 2 ) )
              .replace( "$sp", nameSP ).replace( "$bd", nameDatabase ),
          e.getLocalizedMessage(), e, Constantes.STATUS_TIME_OUT );
    }
    else{
      logger.info( messageTransaction + Constantes.ERROREXCEPTION, e );
      throw new DBException(
          specialistCallProperties.getStringValueProperties( IDTAndIDFKeyProperties.IDT2.getListaValores().get( 1 ) ),
          specialistCallProperties.getStringValueProperties( IDTAndIDFKeyProperties.IDT2.getListaValores().get( 2 ) )
              .replace( "$sp", nameSP ).replace( "$bd", nameDatabase ),
          e.getLocalizedMessage(), e, Constantes.STATUS_DISPONIBILIDAD );
    }
  }
}
