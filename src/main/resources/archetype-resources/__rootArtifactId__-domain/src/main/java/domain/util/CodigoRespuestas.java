package ${package}.domain.util;

public enum CodigoRespuestas{
  CODIGO_OBTENER_PO_CBIO_CORRECTO( "0OBTENER_PO_CBIO" );
  
  String codigo;
  
  public String getCodigo(){
    return codigo;
  }
  
  private CodigoRespuestas( String codigo ){
    this.codigo = codigo;
  }
}
