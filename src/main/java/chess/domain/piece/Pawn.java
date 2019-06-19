package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Piece;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public class Pawn extends Piece {
    private static final String NAME = "p";
    private static final List<Direction> MOVABLE_DIRECTION = Arrays.asList(N, S);

    public Pawn(Team team) {
        super(team, (source, target) -> {
            Direction direction = source.direction(target);

            if (MOVABLE_DIRECTION.stream().noneMatch(movable -> movable == direction)) {
                throw new InvalidDirectionException("움직일 수 있는 방향이 아닙니다.");
            }

            if (source.distance(target, direction) != 1) {
                throw new InvalidDistanceException("움직일 수 있는 거리가 아닙니다.");
            }

            return true;
        });
    }

    @Override
    public String getName() {
        return NAME;
    }
}
