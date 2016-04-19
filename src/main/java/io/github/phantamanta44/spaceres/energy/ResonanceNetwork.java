package io.github.phantamanta44.spaceres.energy;

import io.github.phantamanta44.spaceres.energy.INetworkable.INetworkUpdateHook;
import io.github.phantamanta44.spaceres.util.PhantaUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.minecraft.tileentity.TileEntity;

public class ResonanceNetwork {

	private static final Predicate<INetworkable> NULL_FILTER = i -> i != null;
	private List<INetworkable> members = new CopyOnWriteArrayList<>();
	private Set<TileEntity> scanned = new HashSet<>();
	
	public ResonanceNetwork(INetworkable init) {
		members.add(init);
	}

	public void merge(ResonanceNetwork network) {
		network.members.forEach(m -> m.setNetwork(this));
		rescan();
	}
	
	public void drop(INetworkable elem) {
		members.remove(elem);
		if (elem instanceof TileEntity)
			rescan();
	}

	public void rescan() {
		Optional<TileEntity> maybeTile = members.stream()
				.filter(m -> m instanceof TileEntity)
				.map(m -> (TileEntity)m)
				.findAny();
		members.removeIf(m -> {
			if (m == null)
				return true;
			if (m instanceof TileEntity) {
				m.setNetwork(null);
				return true;
			}
			return false;
		});
		if (!maybeTile.isPresent())
			return;
		scan(maybeTile.get());
		scanned.clear();
		members.stream()
				.filter(m -> m instanceof INetworkUpdateHook)
				.forEach(m -> ((INetworkUpdateHook)m).onNetworkUpdate());
	}
	
	private void scan(TileEntity tile) {
		scanned.add(tile);
		INetworkable member = (INetworkable)tile;
		members.add(member);
		member.setNetwork(this);
		PhantaUtil.iterAdjTiles(tile, (t, f) -> {
			if (t instanceof INetworkable && !scanned.contains(t))
				scan(t);
		});
	}
	
	public INetworkable findUnit(Predicate<INetworkable> filter) {
		return members.stream()
				.filter(NULL_FILTER.and(filter))
				.findAny().orElse(null);
	}
	
	public Stream<INetworkable> stream() {
		return members.stream();
	}
	
}
