package com.cs455.project2.shared;

import com.cs455.project2.serverside.exception.ReplayException;

public class MessageIDCounter {
  private static final MessageIDCounter instance = new MessageIDCounter();
  private int nonce;

  private MessageIDCounter() {
    this.nonce = 0;
  }

  public static MessageIDCounter getInstance() {
    return instance;
  }

  public int getNextNonce() {
    return ++nonce;
  }

  /**
   * Checks to see that the nonce doesn't indicate a replay. If so, throws an exception.
   *
   * @param count
   * @throws ReplayException if the nonce indicates a replay
   */
  public void checkAndSetNonce(int count) throws ReplayException {
    if (count <= this.nonce) {
      throw new ReplayException("Replay Detected");
    } else this.nonce = count;
  }
}
