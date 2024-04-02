package com.veronica.nodamageparticles.listener;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedParticle;
import org.bukkit.Particle;
import com.veronica.nodamageparticles.NoDamageParticles;
import com.veronica.nodamageparticles.config.ConfigHandler;

public class DamageParticleListener extends PacketAdapter {

    public DamageParticleListener(NoDamageParticles plugin) {
        super(plugin, PacketType.Play.Client.ADVANCEMENTS, PacketType.Play.Server.WORLD_PARTICLES);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        // 아무 작업도 수행하지 않음
    }


    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        WrappedParticle wrappedParticle = packet.getNewParticles().read(0);


        // 파티클 타입이 DAMAGE_INDICATOR 인 동시에 콘피그의 use-remove-damage-particle 가 true 인 경우
        if (wrappedParticle.getParticle() == Particle.DAMAGE_INDICATOR &&
                ConfigHandler.getInstance().getConfigIsUse()){
            event.setCancelled(true);
        }
    }
}

