package ch.kchmiel.airpollution.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
}
