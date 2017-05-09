package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.baikalinvestbank.parser.parser;

@Configuration
public class BeenConfig {

    @Bean(name="parser_bean")
    public parser parser() {
        return new parser();
    }
}
