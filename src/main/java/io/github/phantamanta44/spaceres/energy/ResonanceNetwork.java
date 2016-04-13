package io.github.phantamanta44.spaceres.energy;

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

	private List<INetworkable> members = new CopyOnWriteArrayList<>();
	private Set<TileEntity> scanned = new HashSet<>();
	
	public ResonanceNetwork(INetworkable init) {
		join(init);
	}

	public void join(INetworkable member) {
		members.add(member);
	}
	
	public void unjoin(INetworkable member) {
		members.remove(member);
	}

	public void merge(ResonanceNetwork network) {
		network.members.forEach(m -> m.setNetwork(this));
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
	}
	
	private void scan(TileEntity tile) {
		scanned.add(tile);
		if (tile instanceof INetworkable)
			members.add((INetworkable)tile);
		PhantaUtil.iterAdjTiles(tile, (t, f) -> {
			if (!scanned.contains(t))
				scan(t);
		});
	}
	
	public INetworkable findUnit(Predicate<INetworkable> filter) {
		return members.stream()
				.filter(filter)
				.findAny().orElse(null);
	}
	
	public Stream<INetworkable> stream() {
		return members.stream();
	}
	
}
