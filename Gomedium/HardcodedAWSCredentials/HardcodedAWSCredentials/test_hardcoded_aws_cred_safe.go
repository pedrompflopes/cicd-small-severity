package main

import (
	"context"

	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-sdk-go-v2/config"
)

func HandleCredentials(ctx context.Context, evt events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	// ...

	cfg, err := config.LoadDefaultConfig(ctx)
	if err != nil {
		return r, err
	}

	// ...
}
