package com.org.seratic.lucky.accessData.control;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.Entity;

public abstract class EntityController {
	public abstract boolean create(Entity e);
	public abstract boolean edit(Entity e);
	public abstract boolean remove(Entity e);
	public abstract List<Entity> getAll();
}
