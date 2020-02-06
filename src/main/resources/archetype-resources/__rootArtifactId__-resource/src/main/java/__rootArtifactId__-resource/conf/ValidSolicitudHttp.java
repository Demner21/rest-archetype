package ${package}.resource.conf;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.util.HeaderRequestBean;

public class ValidSolicitudHttp{
  
  public boolean isValidaDataHeaderBean( HeaderRequestBean headerRequestBean ){
    if( headerRequestBean.getIdTransaccion() == null ){
      return false;
    }
    if( headerRequestBean.getAccept() == null ){
      return false;
    }
    return headerRequestBean.getAccept().equals( Constantes.TYPEREQUEST );
  }
}
