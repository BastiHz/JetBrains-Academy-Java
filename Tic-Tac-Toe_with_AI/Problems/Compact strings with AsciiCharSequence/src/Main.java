import java.util.*;

class AsciiCharSequence implements CharSequence {
    byte[] sequence;

    public AsciiCharSequence(byte[] bytes) {
        this.sequence = bytes.clone();
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int index) {
        return (char) sequence[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(sequence, start, end));
    }

    @Override
    public String toString() {
        return new String(sequence);
    }

}