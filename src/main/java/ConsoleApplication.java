import controller.ConsoleController;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import model.Machine;
import model.cpu.ArithmeticLogicUnit;
import model.cpu.ControlUnit;
import model.cpu.InstructionRegister;
import model.cpu.ProgramCounter;
import model.cpu.RegisterSet;
import model.cpu.code.Assembler;
import model.memory.RandomAccessMemory;
import utill.InstructionReader;
import view.ConsoleView;

public class ConsoleApplication {

    private static void run() throws IOException {
        List<String> instructions = InstructionReader.read(
            Objects.requireNonNull(ConsoleApplication.class.getClassLoader().getResource("instructions.txt")).getFile());
        Assembler assembler = new Assembler();
        List<Integer> machineLanguage = assembler.assembly(instructions);

        ConsoleController controller = new ConsoleController(
            new Machine(
                new ControlUnit(),
                new ArithmeticLogicUnit(),
                new ProgramCounter(),
                new InstructionRegister(),
                new RegisterSet(),
                new RandomAccessMemory(10, machineLanguage.toArray(new Integer[0]))),
            new ConsoleView()
        );
        controller.execute();
    }

    public static void main(String[] args) throws IOException {
        run();
    }

}
