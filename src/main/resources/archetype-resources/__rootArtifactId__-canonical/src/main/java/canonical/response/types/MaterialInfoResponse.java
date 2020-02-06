#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.canonical.response.types;

public class MaterialInfoResponse{
  
  private String              codSerie;

  public String getCodSerie(){
    return codSerie;
  }
  
  public void setCodSerie( String codSerie ){
    this.codSerie = codSerie;
  }
 

}
