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
        + " - fetch: 현재 PC값에 해당하는 메모리에 있는 명령어를 IR에 저장한다. " + System.lineSeparator()
        + " - execute: IR에 저장된 명령어를 CU가 해독하고, 명령을 수행한다." + System.lineSeparator()
        + " - reset: 현제 레지스터의 값을 모두 초기화 한다" + System.lineSeparator()
        + " - dump: 현재 레지스터의 상태를 보여준다." + System.lineSeparator()
        + " - exit: 프로그램 종료";

    public ConsoleController(Machine machine, ConsoleView view) {
        this.machine = machine;
        this.view = view;
    }

    public void execute() {
        view.printResult(machine.toString());

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
                machine.fetch();

                yield machine.toString();
            }
            case Commands.EXECUTE -> {
                machine.execute();

                yield machine.toString();
            }
            case Commands.RESET -> {
                machine.reset();

                yield machine.toString();
            }
            case Commands.DUMP -> {
                yield machine.toString();
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
