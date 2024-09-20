package ru.clevertec.commands;

public abstract class Command {
    protected CommandHist commandHist;
    public abstract void doSmth();
}
