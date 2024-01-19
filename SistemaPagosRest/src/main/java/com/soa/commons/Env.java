package com.soa.commons;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.reloading.ReloadingControllerSupport;

public class Env {

    private static final String PROPERTIES_FILE_PATH = "properties.file.path";

    /** Contenedor dinámico de archivo de propiedades. */
    private static PropertiesConfiguration configuration = null;

    /** Properties configuration builder. */
    private static FileBasedConfigurationBuilder<PropertiesConfiguration> 
        builder = null;

    /*
     * Inicializador del contenedor dinámico de archivo de propiedades.
     */
    static {
        String path = null;
        try {
            path = System.getProperty(PROPERTIES_FILE_PATH);
            Parameters params = new Parameters();
            builder = 
                    new ReloadingFileBasedConfigurationBuilder<>(
                    PropertiesConfiguration.class)
                    .configure(params.properties().setFileName(path));

            // Register an event listener for triggering reloading checks
            builder.addEventListener(
                ConfigurationBuilderEvent.CONFIGURATION_REQUEST,
                new EventListener<ConfigurationBuilderEvent>() {
                    @Override
                    public void onEvent(ConfigurationBuilderEvent event) {
                        System.out.println("Reloading event triggered");
                        ((ReloadingControllerSupport) builder)
                        .getReloadingController()
                        .checkForReloading(null);
                        
                    }
                });
            configuration = builder.getConfiguration();
            builder.setAutoSave(true);
        } catch (Throwable e) {
            throw new AppException(
                    "Nombre del archivo no identificado[" + path + "]", e);
        }

    }

    /**
     * Obtiene una propiedad configurada sin permitir valores vacíos.
     *
     * @param key Nombre clave de la propiedad a buscar.
     * @return Valor de la propiedad buscada.
     */
    public static synchronized String getProperty(final String key) {
        try {
            configuration = builder.getConfiguration();
            final String result = (String) configuration.getProperty(key);
            if (result == null || result.isEmpty()) {
                throw new AppException(
                        "Nombre de la propiedad no identificado[" + key + "]");
            }
            return result;
        } catch (Exception e) {
            throw new AppException(
                    "Error al leer la propiedad[" + key + "] "
                            + AppException.getStackTrace(e));
        }
    }
}
