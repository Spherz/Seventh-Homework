FROM gradle:jdk17

WORKDIR /seventhhw

COPY . /seventhhw

CMD ["gradle", "bootRun"]