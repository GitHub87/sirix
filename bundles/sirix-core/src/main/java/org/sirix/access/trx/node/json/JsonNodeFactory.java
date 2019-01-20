package org.sirix.access.trx.node.json;

import javax.annotation.Nonnegative;
import org.sirix.access.trx.node.NodeFactory;
import org.sirix.node.json.JsonArrayNode;
import org.sirix.node.json.JsonBooleanNode;
import org.sirix.node.json.JsonNullNode;
import org.sirix.node.json.JsonNumberNode;
import org.sirix.node.json.JsonObjectKeyNode;
import org.sirix.node.json.JsonObjectNode;
import org.sirix.node.json.JsonStringNode;

/**
 * Node factory for creating nodes.
 *
 * @author Johannes Lichtenberger
 *
 */
public interface JsonNodeFactory extends NodeFactory {
  /**
   * Create a {@link JsonArrayNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   */
  JsonArrayNode createJsonArrayNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey);

  /**
   * Create a {@link JsonObjectNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   */
  JsonObjectNode createJsonObjectNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey);

  /**
   * Create a {@link JsonObjectKeyNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   * @param pathNodeKey path node key of node
   * @param name the name of the key
   */
  JsonObjectKeyNode createJsonObjectKeyNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey,
      long pathNodeKey, String name);

  /**
   * Create a {@link JsonStringNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   * @param pathNodeKey path node key of node
   * @param value the value to store
   * @param isCompressed {@code true}, if the value is compressed, {@code false} otherwise
   */
  JsonStringNode createJsonStringNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey,
      byte[] value, boolean isCompressed);

  /**
   * Create a {@link JsonBooleanNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   * @param boolValue the boolean value
   */
  JsonBooleanNode createJsonBooleanNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey,
      boolean boolValue);

  /**
   * Create a {@link JsonNumberNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   * @param dblValue the number value
   */
  JsonNumberNode createJsonNumberNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey,
      double dblValue);

  /**
   * Create a {@link JsonNullNode}.
   *
   * @param parentKey parent node key
   * @param leftSibKey left sibling key
   * @param rightSibKey right sibling key
   */
  JsonNullNode createJsonNullNode(@Nonnegative long parentKey, @Nonnegative long leftSibKey, long rightSibKey);
}
