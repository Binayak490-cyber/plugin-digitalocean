package io.kestra.plugin.digitalocean.monitoring.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Builder
@Getter
@Jacksonized
public class SlackAlert {

    @Schema(title = "Slack incoming webhook URL (input only; masked in outputs)", format = "password")
    private final String url;

    @NotNull
    @Schema(title = "Slack channel name")
    private final String channel;

    public Map<String, Object> toMap() {
        if (url == null) {
            return Map.of("channel", channel);
        }
        return Map.of(
            "url", url,
            "channel", channel
        );
    }

    public static SlackAlert from(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return SlackAlert.builder()
            .channel(map.get("channel") != null ? map.get("channel").toString() : null)
            .build();
    }
}
