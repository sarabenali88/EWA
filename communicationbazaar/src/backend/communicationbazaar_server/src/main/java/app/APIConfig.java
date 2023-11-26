/**
 * This is a class that will ignore all the CORS rules.
 *
 * @author Jasper Fernhout
 */
package app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Configuration
public class APIConfig implements WebMvcConfigurer {

    /**
     * Adds CORS mappings to allow cross-origin requests from specified origins and methods.
     *
     * @param registry CorsRegistry to configure CORS mappings
     * @author Jasper Fernhout
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", getHostIPAddressPattern())
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    /**
     * Retrieves the host IP address pattern to allow CORS from the local host and a default pattern.
     *
     * @return String representing the host IP address pattern
     * @author Jasper Fernhout
     */
    private String getHostIPAddressPattern() {
        try {
            return "http://" + Inet4Address.getLocalHost().getHostAddress() + ":*";
        } catch (UnknownHostException ignored) {
        }
        return "http://192.168.*.*:*";

    }
}
