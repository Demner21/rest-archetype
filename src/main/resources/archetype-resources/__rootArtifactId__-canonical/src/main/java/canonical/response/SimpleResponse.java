#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.canonical.response;

import java.util.List;
import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.util.ClaroUtil;
import ${package}.canonical.response.types.MaterialInfoResponse;

public class SimpleResponse extends BodyResponse implements GenericCannonicalResponse{
  
  private List<MaterialInfoResponse> listaStockMaterial;
  
  public List<MaterialInfoResponse> getListaMaterial(){
    return listaStockMaterial;
  }
  
  public void setListaMaterial( List<MaterialInfoResponse> listaMaterial ){
    this.listaStockMaterial = listaMaterial;
  }

  public String toJSONString(){
    return ClaroUtil.printJSONString( this );
  }
}
