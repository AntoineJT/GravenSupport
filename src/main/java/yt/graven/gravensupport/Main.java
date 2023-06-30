package yt.graven.gravensupport;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("yt.graven.gravensupport")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Startup startup = context.getBean(Startup.class);
        startup.run();
    }

    @Component
    @RequiredArgsConstructor
    private static class Startup {

        private final EventReceiver eventReceiver;
        private final JDA client;

        public void run() {
            this.client
                    .getPresence()
                    .setPresence(Activity.listening("/ticket | Ouvrez un ticket avec la modération"), false);

            this.client.addEventListener(eventReceiver);
        }
    }
}
