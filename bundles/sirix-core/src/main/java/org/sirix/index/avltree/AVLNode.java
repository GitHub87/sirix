package org.sirix.index.avltree;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.brackit.xquery.atomic.QNm;
import org.sirix.index.avltree.interfaces.MutableAVLNode;
import org.sirix.index.avltree.keyvalue.CASValue;
import org.sirix.node.AbstractForwardingNode;
import org.sirix.node.NodeKind;
import org.sirix.node.SirixDeweyID;
import org.sirix.node.delegates.NodeDelegate;
import org.sirix.settings.Fixed;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * AVLNode which is mutable.
 *
 * @author Johannes Lichtenberger
 */
public final class AVLNode<K extends Comparable<? super K>, V> extends AbstractForwardingNode
    implements MutableAVLNode<K, V> {
  /** Key token. */
  private K key;

  /** Value. */
  private V value;

  /** Reference to the left node. */
  private long left = Fixed.NULL_NODE_KEY.getStandardProperty();

  /** Reference to the right node. */
  private long right = Fixed.NULL_NODE_KEY.getStandardProperty();

  /** 'changed' status of tree node. */
  private boolean isChanged;

  /** {@link NodeDelegate} reference. */
  private NodeDelegate nodeDelegate;

  private AVLNode<K,V> leftChild;

  private AVLNode<K,V> rightChild;

  /**
   * Constructor.
   *
   * @param key the key
   * @param value the value
   * @param nodeDelegate the used node delegate
   */
  public AVLNode(final K key, final V value, final NodeDelegate nodeDelegate) {
    this.key = checkNotNull(key);
    this.value = checkNotNull(value);
    this.nodeDelegate = checkNotNull(nodeDelegate);
  }

  @Override
  public NodeKind getKind() {
    if (key instanceof Long) {
      return NodeKind.PATHAVL;
    }
    if (key instanceof CASValue) {
      return NodeKind.CASAVL;
    }
    if (key instanceof QNm) {
      return NodeKind.NAMEAVL;
    }
    return NodeKind.UNKNOWN;
  }

  @Override
  protected NodeDelegate delegate() {
    return nodeDelegate;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  /**
   * Flag which determines if node is changed.
   *
   * @return {@code true} if it has been changed in memory, {@code false} otherwise
   */
  @Override
  public boolean isChanged() {
    return isChanged;
  }

  @Override
  public void setChanged(final boolean changed) {
    isChanged = changed;
  }

  @Override
  public boolean hasLeftChild() {
    return left != Fixed.NULL_NODE_KEY.getStandardProperty();
  }

  @Override
  public boolean hasRightChild() {
    return right != Fixed.NULL_NODE_KEY.getStandardProperty();
  }

  @Override
  public long getLeftChildKey() {
    return left;
  }

  @Override
  public long getRightChildKey() {
    return right;
  }

  public AVLNode<K, V> getLeftChild() {
    return leftChild;
  }

  public AVLNode<K, V> getRightChild() {
    return rightChild;
  }

  public void setLeftChild(AVLNode<K, V> leftChild) {
    this.leftChild = leftChild;
  }

  public void setRightChild(AVLNode<K, V> rightChild) {
    this.rightChild = rightChild;
  }

  @Override
  public void setLeftChildKey(final long left) {
    this.left = left;
  }

  @Override
  public void setRightChildKey(final long right) {
    this.right = right;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nodeDelegate.getNodeKey());
  }

  @Override
  public boolean equals(final @Nullable Object obj) {
    if (obj instanceof AVLNode) {
      @SuppressWarnings("unchecked")
      final AVLNode<K, V> other = (AVLNode<K, V>) obj;
      return this.nodeDelegate.getNodeKey() == other.nodeDelegate.getNodeKey();
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
                      .add("node delegate", nodeDelegate)
                      .add("left child", left)
                      .add("right child", right)
                      .add("changed", isChanged)
                      .add("key", key)
                      .add("value", value)
                      .toString();
  }

  @Override
  public void setKey(final K key) {
    this.key = checkNotNull(key);
  }

  @Override
  public void setValue(final V value) {
    this.value = checkNotNull(value);
  }

  @Override
  public SirixDeweyID getDeweyID() {
    return null;
  }
}
