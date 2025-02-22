package laddergame;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Reward {

    private static final Reward 꽝 = new Reward("꽝");

    private final String value;

    private Reward(final String value) {
        this.value = value;
    }

    public static Reward of(String value) {
        if ("꽝".equals(value)) {
            return 꽝;
        }
        return new Reward(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Reward)) return false;
        final Reward reward = (Reward) o;
        return Objects.equals(value, reward.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
