package laddergame;

import lombok.Getter;

public class Player {

    private static final String INVALID_NAME_MESSAGE = "플레이어 이름은 공백이 아닌 글자 1글자 이상 5글자 이하여야 합니다";

    public static final int MAX_LENGTH = 5; // todo  - Player 에 둬야 하는게 맞는 걸까?

    @Getter
    private final String name;

    public Player(final String name) {
        if (name == null || name.isBlank() || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_MESSAGE);
        }
        this.name = name;
    }
}
