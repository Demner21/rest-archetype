#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.canonical.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.JAXBUtilitarios;

public class SimpleRequest implements GenericCannonicalRequest{
  
  private String codMaterial;
  @NotNull
  @Size( min = 1 )
  private String codInterlocutor;
  private String tipMaterial;
  private String serEquipo;
  
  public String getCodMaterial(){
    return codMaterial;
  }
  
  public void setCodMaterial( String codMaterial ){
    this.codMaterial = codMaterial;
  }
  
  public String getCodInterlocutor(){
    return codInterlocutor;
  }
  
  public void setCodInterlocutor( String codInterlocutor ){
    this.codInterlocutor = codInterlocutor;
  }
  
  public String getTipMaterial(){
    return tipMaterial;
  }
  
  public void setTipMaterial( String tipMaterial ){
    this.tipMaterial = tipMaterial;
  }
  
  public String getSerEquipo(){
    return serEquipo;
  }
  
  public void setSerEquipo( String serEquipo ){
    this.serEquipo = serEquipo;
  }
  
  public String toXML(){
    return JAXBUtilitarios.anyObjectToXmlText( this );
  }

  public String toJSON(){
    return ClaroUtil.printJSONString( this );
  }
}
