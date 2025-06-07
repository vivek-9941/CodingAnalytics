package org.vivek.platform.Service.Implementation;

import org.springframework.web.reactive.function.client.WebClient;
import org.vivek.platform.Service.CodechefService;

public class CodechefserviceImpl implements CodechefService {
    private final WebClient webClient;

    public CodechefserviceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void fetchapi(String username) {

    }

    @Override
    public void schedule() {

    }
}
