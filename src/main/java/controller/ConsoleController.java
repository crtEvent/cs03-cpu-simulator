package controller;

import java.util.Objects;
import java.util.function.Function;
import model.Machine;
import view.ConsoleView;

public class ConsoleController {

    private final Machine machine;
    private final ConsoleView view;

    private static final Function<String, String> COMMAND_ERROR_MESSAGE = (String command)
        -> String.format("'%s'는 잘못된 명령어 입니다.", command);
    private static final String HELP_MESSAGE = "[도움말]" + System.lineSeparator()
        + " - exit: 프로그램 종료";

    public ConsoleController(Machine machine, ConsoleView view) {
        this.machine = machine;
        this.view = view;
    }

    public void execute() {
        while (true) {
            try {
                String command = view.inputCommand();
                String result = generateResult(command);
                if (Objects.equals(Commands.EXIT, result)) break;
                view.printResult(result);
            } catch (Exception e) {
                view.printErrorMessage(e);
            }
        }
    }

    private String generateResult(String command) {
        return switch(command) {
            case Commands.FETCH -> {
                yield Commands.FETCH;
            }
            case Commands.EXECUTE -> {
                yield Commands.EXECUTE;
            }
            case Commands.RESET -> {
                yield Commands.RESET;
            }
            case Commands.DUMP -> {
                yield Commands.DUMP;
            }
            case Commands.HELP -> {
                yield HELP_MESSAGE;
            }
            case Commands.EXIT -> {
                yield Commands.EXIT;
            }
            default -> throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
        };
    }
}
