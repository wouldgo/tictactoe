FROM ubuntu
MAINTAINER Dario Andrei <wouldgo84@gmail.com>

RUN apt-get upgrade -y && \
  apt-get update && \
  apt-get install -y software-properties-common python-software-properties && \
  add-apt-repository ppa:webupd8team/java && \
  apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer && apt-get install oracle-java8-set-default

RUN cd /tmp && wget http://mirrors.muzzy.it/apache/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz && tar xvf apache-maven-3.3.3-bin.tar.gz && rm apache-maven-3.3.3-bin.tar.gz && mv apache-maven-3.3.3 ../
RUN apt-get install -y git
RUN git clone https://github.com/wouldgo/tictactoe.git && cd tictactoe && /apache-maven-3.3.3/bin/mvn clean install

EXPOSE 8081
ADD ./run/bootstrap.sh /opt/bootstrap.sh

CMD ["/bin/bash", "/opt/bootstrap.sh" ]
