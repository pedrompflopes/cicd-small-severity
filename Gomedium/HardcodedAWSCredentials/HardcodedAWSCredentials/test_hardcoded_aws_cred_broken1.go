package main

import (
	"context"

	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-sdk-go-v2/aws"
	"github.com/aws/aws-sdk-go-v2/config"
	"github.com/aws/aws-sdk-go-v2/credentials"
	"github.com/aws/aws-sdk-go-v2/service/s3"
)

func HandleCredentials(ctx context.Context, evt events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	// ...

	cfg, err := config.LoadDefaultConfig(ctx)
	if err != nil {
		return r, err
	}

	accessKey := "ACCESS_KEY"
	secretKey := "SECRET_KEY"

	s3client := s3.NewFromConfig(cfg, func(o *s3.Options) {
		o.Credentials = aws.NewCredentialsCache(credentials.NewStaticCredentialsProvider(accessKey, secretKey, ""))
	})

	s3client2 := s3.NewFromConfig(cfg, func(o *s3.Options) {
		o.Credentials = aws.NewCredentialsCache(credentials.NewStaticCredentialsProvider("ACCESS_KEY2", "SECRET_KEY2", ""))
	})

	// ...
}
