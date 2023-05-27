FROM alpine:latest as builder
RUN apk update
RUN apk add openjdk11
RUN jlink --add-modules ALL-MODULE-PATH --output /tmp/jre \
      --no-man-pages --no-header-files --compress=2
FROM alpine:latest
COPY --from=builder /tmp/jre /opt/jre