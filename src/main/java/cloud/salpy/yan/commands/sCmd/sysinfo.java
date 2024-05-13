package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.SlashCommandBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.hyperic.sigar.Sigar;

import java.awt.*;

public class sysinfo extends SlashCommandBuilder {
    public sysinfo() {

        this.name = "sysinfo";
        this.description = "Host system infomation";

    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String cpuname = "";
        try {

            Sigar sigar = new Sigar();
            org.hyperic.sigar.CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
            for (org.hyperic.sigar.CpuInfo info : cpuInfoList) {
                cpuname = info.getModel();
            }
        }
        catch (Exception e) {
            event.reply(e.toString()).setEphemeral(true).queue();
            return;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setDescription("```\n" +
                                "Cpu name: " + cpuname + "\n" +
                                "Java Version: " + System.getProperty("java.version") + "\n" +
                                "JDA Module: " + event.getJDA().getClass().getPackage().getImplementationVersion() + "\n" + "```"
                )
                .setColor(new Color((int)(Math.random() * 0x1000000)));
        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
