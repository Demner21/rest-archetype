package ${package}.resource.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import pe.com.claro.common.exception.ProviderExceptionMapper;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.GeneralRuntimeException;
import ${package}.resource.${nameClass}Resource;

@Singleton
@ApplicationPath( Constantes.API )
public class ApplicationConfig extends Application{
  
  private static final String CLARO_POST_TIPOPRODUCTO = "claro-post-tipoproducto";
  private static final String CLARO_PROPERTIES        = "claro.properties";
  private static final String PROPERTIES              = ".properties";
  
  @Override
  public Set<Class<?>> getClasses(){
    Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
    resources.add( ${nameClass}Resource.class );
    resources.add( ProviderExceptionMapper.class );
    resources.add( com.wordnik.swagger.jaxrs.listing.ApiListingResource.class );
    resources.add( com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class );
    resources.add( com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class );
    resources.add( com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class );
    return resources;
  }
  
  public Map<String, Object> getProperties(){
    String nombrePropertieExterno = PROPERTIES;
    Map<String, Object> dataProperties = new HashMap<String, Object>();
    dataProperties.putAll( readProperties( nombrePropertieExterno, false ) );
    return dataProperties;
  }
  
  private Map<String, Object> readProperties( String fileInClasspath, Boolean interno ){
    InputStream is = null;
    String urlServer = "";
    try{
      if( interno ){
        is = this.getClass().getClassLoader().getResourceAsStream( fileInClasspath );
      }
      else{
        String claroProperties = System.getProperty( CLARO_PROPERTIES );
        urlServer = claroProperties + CLARO_POST_TIPOPRODUCTO + File.separator + fileInClasspath;
        is = new FileInputStream( urlServer );
        // is = this.getClass().getClassLoader().getResourceAsStream( urlServer );
      }
      Map<String, Object> map = new HashMap<String, Object>();
      Properties properties = new Properties();
      properties.load( is );
      map.putAll(
          properties.entrySet().stream()
                              .collect( 
                                  Collectors.toMap( e -> e.getKey()
                                                        .toString(), 
                                                        e -> e.getValue() ) ) );
      is.close();
      return map;
    }
    catch( Exception e ){
      throw new GeneralRuntimeException( "No se puede leer el archivo " + fileInClasspath, e );
    }
  }
}
