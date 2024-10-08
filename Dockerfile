FROM ubuntu:12.04
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y openjdk-6-jdk curl git-core build-essential bzr
RUN mkdir -p /tmp/downloads

# install go
RUN curl -sf -o /tmp/downloads/go1.1.1.linux-amd64.tar.gz -L https://go.googlecode.com/files/go1.1.1.linux-amd64.tar.gz
RUN mkdir -p /opt && cd /opt && tar xfz /tmp/downloads/go1.1.1.linux-amd64.tar.gz

# install jenkins
RUN curl -sf -o /opt/jenkins-1.523.war -L http://mirrors.jenkins-ci.org/war/1.523/jenkins.war

# install plugins
RUN mkdir -p /data/jenkins/plugins
RUN curl -sf -o /data/jenkins/plugins/chucknorris.hpi -L http://mirrors.jenkins-ci.org/plugins/chucknorris/latest/chucknorris.hpi
RUN curl -sf -o /data/jenkins/plugins/greenballs.hpi -L http://mirrors.jenkins-ci.org/plugins/greenballs/latest/greenballs.hpi
RUN curl -sf -o /data/jenkins/plugins/git-client.hpi -L http://mirrors.jenkins-ci.org/plugins/git-client/latest/git-client.hpi
RUN curl -sf -o /data/jenkins/plugins/git.hpi -L http://mirrors.jenkins-ci.org/plugins/git/latest/git.hpi

# env stuff
ENV JENKINS_HOME /data/jenkins
ENV GOROOT /opt/go
ENV GOPATH /root/gocode
ENV PATH /opt/go/bin:/root/gocode/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin

EXPOSE 8080
CMD cd /opt && java -jar jenkins-1.523.war