package edu.nyu.cs9053.homework11.network;

import edu.nyu.cs9053.homework11.game.Difficulty;
import edu.nyu.cs9053.homework11.game.GameProvider;
import edu.nyu.cs9053.homework11.game.screen.InputMove;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
/**
 * User: blangel
 *
 * A blocking IO implementation of a client which requests moves from a remote server implementing the
 * {@linkplain edu.nyu.cs9053.homework11.network.NetworkGameProvider}
 */
public class GameClient implements GameProvider {
    public final static String NEXT_FOES = "NEXT_FOES";
    public final static String NEXT_MOVE = "NEXT_MOVE";

    public static GameClient construct(Difficulty difficulty) {
        try {
           Socket connection = new Socket(GameServer.SERVER_HOST, GameServer.SERVER_PORT);
           return new GameClient(difficulty, connection.getInputStream(), connection.getOutputStream());
        } catch (IOException ioe) {
            System.out.printf("Failed Connection: %s\n", ioe.getMessage());
            throw new RuntimeException("Failed Connection");
        }
    }

    private final Difficulty difficulty;
    private final InputStream serverInput;
    private final OutputStream serverOut;

    public GameClient(Difficulty difficulty, InputStream serverInput, OutputStream serverOutput) {
        this.difficulty = difficulty;
        this.serverInput = serverInput;
        this.serverOut = serverOutput;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public int getRandomNumberOfNextFoes() {
        PrintWriter printer = new PrintWriter(this.serverOut, true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.serverInput, StandardCharsets.UTF_8));
        String randomNumberOfNextFoes;
        try {
            send(printer, difficulty.getLevel(), NEXT_FOES);
            randomNumberOfNextFoes = reader.readLine();
        } catch (IOException ioe) {
            throw new RuntimeException("Failed to get foes:" + ioe.getMessage());
        }
        return Integer.valueOf(randomNumberOfNextFoes);
    }

    @Override
    public InputMove getRandomNextMove() {
        PrintWriter printer = new PrintWriter(this.serverOut, true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.serverInput, StandardCharsets.UTF_8));
        String randomNextMove;
        try {
            send(printer, difficulty.getLevel(), NEXT_MOVE);
            randomNextMove = reader.readLine();
        } catch (IOException ioe) {
            throw new RuntimeException("Fail to get message:" + ioe.getMessage());
        }
        switch (randomNextMove) {
            case "Left":
                return InputMove.Left;
            case "Right":
                return InputMove.Right;
            case "Up":
                return InputMove.Up;
            case "Down":
                return InputMove.Down;
            default:
                throw new AssertionError("Invalid Input");
        }

    }

    private void send(PrintWriter out, int difficulty, String operation) {
        out.println(String.format("{operation:%s, difficulty:%d}%n", operation, difficulty));
    }
}
