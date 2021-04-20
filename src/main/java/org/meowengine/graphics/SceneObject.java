package org.meowengine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public abstract class SceneObject implements Iterable<SceneObject>{

    private final List<SceneObject> objects;

    private final Matrix4f model;
    private final Vector3f location;

    protected SceneObject() {
        objects = new LinkedList<>();
        model = new Matrix4f().identity();
        location = new Vector3f();
    }

    protected SceneObject(Vector3f position) {
        objects = new LinkedList<>();
        model = new Matrix4f().identity();
        location = position;
    }

    public void rotate(float angle, Vector3f axis) {
        model.rotate(angle, axis);
    }

    public void rotate(float angle, float axis_x, float axis_y, float axis_z) {
        model.rotate(angle, axis_x, axis_y, axis_z);
    }

    public void scale(Vector3f scale) {
        model.scale(scale);
    }

    public void scale(float xyz) {
        model.scale(xyz);
    }

    public void scale(float x, float y, float z) {
        model.scale(x, y, z);
    }

    public void translate(Vector3f addPos) {
        location.add(addPos);
    }

    public void translate(float x, float y, float z) {
        location.add(x, y, z);
    }

    public void setLocation(Vector3f loc) {
        location.set(loc);
    }

    public void setLocation(float x, float y, float z) {
        location.set(x, y, z);
    }

    //SceneObject prefab array actions

    public boolean add(SceneObject sceneObject) {
        return objects.add(sceneObject);
    }


    public boolean addAll(Collection<? extends SceneObject> c) {
        return objects.addAll(c);
    }


    public boolean remove(Object o) {
        return objects.remove(o);
    }


    public SceneObject remove(int index) {
        return objects.remove(index);
    }


    public int size() {
        return objects.size();
    }

    public void draw() {
        objects.forEach(SceneObject::draw);
    }

    @Override
    public Iterator<SceneObject> iterator() {
        return objects.iterator();
    }

    @Override
    public void forEach(Consumer<? super SceneObject> action) {
        objects.forEach(action);
    }

}
