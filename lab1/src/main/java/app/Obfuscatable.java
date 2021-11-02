package app;

public interface Obfuscatable<T> {
    T obfuscate();

    T unobfuscate();
}
