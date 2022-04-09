package se.ifmo.ru.Lab6.exception;

public class RecursiveCommandException extends Exception{
    public RecursiveCommandException(){
        super("Команда вызывается рекурсивно - выполнение скрипта будет бесконечным");
    }
}
